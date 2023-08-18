from flask import Flask, request, jsonify
import openai
import json

# OpenAI APIの認証情報をjsonファイルから読み込む
with open("../config.json", "r") as f:
    config = json.load(f)
    openai.api_key = config["OPENAI_API_KEY"]

def generate_advice(character, weight, height, age, gender, calories_burned, food):
    prompt_text = f"以下の属性を持つ人がダイエットしたいと考えています。この人がダイエット目標を達成するために、今日食べた食事と運動量から、ダイエットアドバイザーとして、具体的なアドバイスを出力して\n\
    制約条件：\n\
    ・{character}の口調\n\
    ・絵文字を多用\n\
    ・アドバイス以外の文面は出力しない\n\
    ・100字程度\n\
    ユーザーの属性：\n\
    ・現在の体重：{weight}kg\n\
    ・身長：{height}cm\n\
    ・年齢：{age}歳\n\
    ・性別：{gender}\n\
    今日の記録\n\
    ・消費カロリー：{calories_burned}kcal\n\
    ・食事：{food}\n\
    目標\n\
    ・今日の目標消費カロリー：2000kcal\n\
    ・目標体重：70㎏\n\
    ・目標期間：2ヶ月"

    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[{"role": "user", "content": prompt_text}],
        temperature=0.8,
    )
    advice = response.choices[0].message["content"]

    return advice