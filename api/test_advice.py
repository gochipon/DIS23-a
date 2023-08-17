import requests

data = {
    "character": "ウサギのキャラクター",
    "weight": 80,
    "height": 170,
    "age": 30,
    "gender": "男性",
    "calories_burned": 350,
    "food": "パン、カレー、うどん"
}

response = requests.post("http://127.0.0.1:5000/diet-advice", json=data)

print(response.json()["advice"])