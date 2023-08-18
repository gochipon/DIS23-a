from flask import Flask, request, jsonify
import advice_generate as ag
import character_generate as cg
import food_prediction as fp

app = Flask(__name__)

@app.route('/diet-advice', methods=['POST'])
def give_advice():
    data = request.json
    advice = ag.generate_advice(data)
    return jsonify({"advice": advice})

@app.route('/generate', methods=['POST'])
def generate():
    data = request.json
    prompt = cg.create_prompt(data["character_type"], data["character_traits"], data["appearance_attributes"])
    image_url = cg.generate_image(prompt)
    return jsonify({"image_url": image_url})

@app.route('/predict-food', methods=['POST'])
def get_food_prediction():
    data = request.json
    path_to_image = data['path_to_image']
    prediction = fp.predictFood(path_to_image)
    calories = fp.get_calories_for_dish(prediction)
    return jsonify({"food": prediction, "calories_ate": calories})

if __name__ == "__main__":
    app.run(debug=True, port=8080)
