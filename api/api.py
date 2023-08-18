from flask import Flask, request, jsonify
import advice_generate as ag
import character_generate as cg
import food_prediction as fp

app = Flask(__name__)

@app.route('/diet-advice', methods=['POST'])
def give_advice():
    data = request.json
    character = data["character"]
    weight = data["weight"]
    height = data["height"]
    age = data["age"]
    gender = data["gender"]
    calories_burned = data["calories_burned"]
    food = data["food"]
    advice = ag.generate_advice(character, weight, height, age, gender, calories_burned, food)
    return jsonify({"advice": advice})

@app.route('/generate', methods=['POST'])
def generate():
    data = request.json
    prompt = cg.create_prompt(data["character_type"], data["character_traits"], data["appearance_attributes"], data["special_features"], data["animal_attributes"])
    image_url = cg.generate_image(prompt)
    return jsonify({"image_url": image_url})

@app.route('/predict-food', methods=['POST'])
def get_food_prediction():
    data = request.json
    path_to_image = data['path_to_image']
    prediction = fp.predictFood(path_to_image)
    calories = fp.get_calories_for_dish(prediction)
    return jsonify({"predicted_food": prediction, "calories": calories})

if __name__ == "__main__":
    app.run(debug=True)
