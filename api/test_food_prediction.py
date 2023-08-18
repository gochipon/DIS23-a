import requests
import json

API_ENDPOINT = "http://127.0.0.1:5000/predict-food"

data = {
    "path_to_image": "../ramen.jpg"  # これを実際の画像のパスに置き換えてください
}

response = requests.post(API_ENDPOINT, json=data)

print("Response Code:", response.status_code)
print(json.loads(response.text))
