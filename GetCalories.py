import requests
import json

def get_calories_for_dish(dish_name, app_id, app_key):
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

# Define your app_id and app_key here
app_id = "928a7ce6"
app_key = "65b7dce2447bdeb43f6108e327630afe"

if __name__ == "__main__":
    dish_name = input("Enter the dish name: ")
    try:
        calories = get_calories_for_dish(dish_name, app_id, app_key)
        print(f"The estimated calorie content of {dish_name} is: {calories} kcal")
    except ValueError as e:
        print(e)