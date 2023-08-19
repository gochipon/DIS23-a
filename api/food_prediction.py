from PIL import Image
from tensorflow import compat
import tensorflow_hub as hub
import numpy as np
import pandas as pd
import cv2
from skimage import io
import imageio as iio
import json
import requests
import base64
import io

def api_predict(encoded_image):
    # Base64デコードしてPIL画像オブジェクトに変換
    decoded_image = base64.b64decode(encoded_image)
    image = Image.open(io.BytesIO(decoded_image))
    
    # サイズ変更
    image = image.resize((224, 224))
    
    # 画像をnumpy arrayに変換
    image_array = np.array(image)

    # predictFoodを呼び出し
    return predictFood(image_array)

def predictFood(bitmap):
    m = hub.KerasLayer('https://tfhub.dev/google/aiy/vision/classifier/food_V1/1')
    
    # Convert bitmap to numpy array
    image = np.array(bitmap)
    if image is None:
        print("Error loading image")
        return
    print(image.dtype)
    print(image.shape)

    labelmap_url = "https://www.gstatic.com/aihub/tfhub/labelmaps/aiy_food_V1_labelmap.csv"
    input_shape = (224, 224)

    image = cv2.resize(image, dsize=input_shape, interpolation=cv2.INTER_CUBIC)
    # Scale values to [0, 1].
    image = image / image.max()
    # The model expects an input of (?, 224, 224, 3).
    images = np.expand_dims(image, 0)
    # This assumes you're using TF2.
    output = m(images)
    predicted_index = output.numpy().argmax()
    classes = list(pd.read_csv(labelmap_url)["name"])
    print("Prediction: ", classes[predicted_index])
    return classes[predicted_index]

def load_config():
    with open("./config.json", "r") as file:
        config = json.load(file)
    return config

config = load_config()
food_app_id = config["food_app_id"]
food_app_key = config["food_app_key"]

def get_calories_for_dish(dish_name, app_id=food_app_id, app_key=food_app_key):
    # API endpoint and parameters
    endpoint = "https://api.edamam.com/api/food-database/parser"
    params = {
        "ingr": dish_name,
        "app_id": app_id,
        "app_key": app_key
    }
    
    # Make the request
    response = requests.get(endpoint, params=params)
    data = response.json()
    
    # Check if we got valid results
    if "parsed" in data and len(data["parsed"]) > 0:
        food = data["parsed"][0]["food"]
        if "nutrients" in food and "ENERC_KCAL" in food["nutrients"]:
            return food["nutrients"]["ENERC_KCAL"]
    
    # If there's an error or unexpected structure
    raise ValueError(f"Could not find calorie data for {dish_name}")

# path_to_image = "/home/hirokisawada/Desktop/CurryRice.png"
# predictedFood = predictFood(path_to_image)
# get_calories_for_dish(predictedFood)
# from PIL import Image

# # 画像を読み込む
# image_path = "../ramen.jpg"
# with Image.open(image_path) as img:
#     # PIL ImageオブジェクトをNumpy配列（bitmap）に変換
#     bitmap = np.array(img)

# # predictFood関数でbitmapを使って食品を予測
# predicted_food = predictFood(bitmap)
# print(f"Predicted food is: {predicted_food}")

# # 予測された食品のカロリーを取得
# try:
#     calories = get_calories_for_dish(predicted_food)
#     print(f"Calories for {predicted_food}: {calories} kcal")
# except ValueError as e:
#     print(e)
