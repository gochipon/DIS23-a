from flask import Flask, request, jsonify
import openai
import json

# OpenAI APIの認証情報をjsonファイルから読み込む
with open("../config.json", "r") as f:
    config = json.load(f)
    openai.api_key = config["OPENAI_API_KEY"]

# def generate_advice(character, weight, height, age, gender, calories_burned, calories_ate, food, target_calories, target_weight, target_period):
#     prompt_text = f"以下の属性を持つ人がダイエットしたいと考えています。この人がダイエット目標を達成するために、今日食べた食事と運動量から、ダイエットアドバイザーとして、具体的なアドバイスを出力して\n\
#     制約条件：\n\
#     ・{character}の口調\n\
#     ・絵文字を多用\n\
#     ・アドバイス以外の文面は出力しない\n\
#     ・100字程度\n\
#     ユーザーの属性：\n\
#     ・現在の体重：{weight}kg\n\
#     ・身長：{height}cm\n\
#     ・年齢：{age}歳\n\
#     ・性別：{gender}\n\
#     今日の記録\n\
#     ・消費カロリー：{calories_burned}kcal\n\
#     ・食事：{food}\n\
#     ・摂取カロリー：{calories_ate}kcal\n\
#     目標\n\
#     ・今日の目標消費カロリー：{target_calories}kcal\n\
#     ・目標体重：{target_weight}㎏\n\
#     ・目標期間：{target_period}日\n\
#     アドバイス："

#     response = openai.ChatCompletion.create(
#         model="gpt-3.5-turbo",
#         messages=[{"role": "user", "content": prompt_text}],
#         temperature=0.8,
#     )
#     advice = response.choices[0].message["content"]

#     return advice

def generate_advice(data):
    prompt_text = f"以下の属性を持つ人がダイエットしたいと考えています。この人がダイエット目標を達成するために、今日食べた食事や運動量の情報から、ダイエットアドバイザーとして具体的なアドバイスを出力して\n\
    制約条件：\n\
    ・{data['character_traits']}な{data['character_type']}のキャラクターの口調を強調\n\
    ・アドバイス以外の文面は出力しない\n\
    ・絵文字を多用\n\
    ・2文以内\n\
    ・50文字以内\n\
    ユーザーの属性：\n\
    ・現在の体重：{data['weight']}kg\n\
    ・身長：{data['height']}cm\n\
    ・年齢：{data['age']}歳\n\
    ・性別：{data['gender']}\n\
    今日の記録\n\
    ・消費カロリー：{data['calories_burned']}kcal\n\
    ・食事：{data['food']}\n\
    ・摂取カロリー：{data['calories_ate']}kcal\n\
    目標\n\
    ・今日の目標消費カロリー：{data['target_calories']}kcal\n\
    ・目標体重：{data['target_weight']}㎏\n\
    ・目標期間：{data['target_period']}日\n\
    アドバイス："

    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[{"role": "user", "content": prompt_text}],
        temperature=0.8,
        max_tokens=80
    )
    advice = response.choices[0].message["content"]

    return advice