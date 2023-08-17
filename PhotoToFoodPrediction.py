from tensorflow import compat
import tensorflow_hub as hub
import numpy as np
import pandas as pd
import cv2
from skimage import io
import imageio as iio

def predictFood(path_to_image):
    m = hub.KerasLayer('https://tfhub.dev/google/aiy/vision/classifier/food_V1/1')
    image = iio.imread(path_to_image)
    labelmap_url = "https://www.gstatic.com/aihub/tfhub/labelmaps/aiy_food_V1_labelmap.csv"
    input_shape = (224, 224)

    # image = np.asarray(io.imread(cake_url), dtype="float")
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

path = "/home/hirokisawada/DATA/Internship/test.jpg"
predictFood(path)