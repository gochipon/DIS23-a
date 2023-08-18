import openai
import json
from PIL import Image
from io import BytesIO

# OpenAI APIの認証情報をjsonファイルから読み込む
with open("../config.json", "r") as f:
    config = json.load(f)
    openai.api_key = config["OPENAI_API_KEY"]

# DALL-Eによる画像生成の関数
def generate_image(prompt):
    response = openai.Image.create(
        model="image-alpha-001",
        prompt=prompt,
        n=1,
        size="512x512",
        response_format="url"
    )
    return response['data'][0]['url']

# 日本語から英語へのマッピング
translation_dict = {
    # Character types
    '男性': 'male',
    '女性': 'female',
    '動物': 'animal',
    
    # Personality traits
    'ポジティブ': 'positive',
    'クール': 'cool',
    'ストイック': 'stoic',
    'ユーモラス': 'humorous',
    'ミステリアス': 'mysterious',
    # ... 他の性格属性も同様に追加
    
    # Appearance attributes
    '人種': 'Race',
    '日本人': 'Japanese',
    '欧米人': 'Western',
    '髪の色': 'hair color',
    'ブラウン': 'brown',
    'ブロンド': 'blond',
    '黒': 'black',
    '赤': 'red',
    '青': 'blue',
    '緑': 'green',
    '白': 'white',
    'パステルカラー': 'pastel',

    '髪のスタイル': 'hair style',
    'ストレート': 'straight',
    'カール': 'curled',
    'ボブ': 'bob',
    'ロング': 'long',
    'ミディアム': 'medium',
    'ショート': 'short',
    'ボルドー': 'bordaux',

    '目の色': 'eye color',
    'ブルー': 'blue',
    'グリーン': 'green',
    'グレー': 'gray',
    '金色': 'gold',
    '紫': 'purple',

    '服装スタイル': 'clothing style',
    'カジュアル': 'casual',
    'スポーティ': 'sporty',
    'フォーマル': 'formal',
    'エレガント': 'elegant',
    'ヴィンテージ': 'vintage',
    'フューチャリスティック': 'futuristic',
    '伝統的': 'traditional',

    '体型': 'body type',
    '細身': 'slim',
    '筋肉質': 'muscular',
    'ぽっちゃり': 'chubby',
    'アスリート': 'athletic',
    'ほっそり': 'slender',

    # Special features
    '猫耳': 'cat ears',
    'アクセサリー': 'accessory',
    'イヤリング': 'earrings',
    'ネックレス': 'necklace',
    'リストバンド': 'wristband',
    '眼鏡': 'glasses',
    '帽子': 'hat',        

    # Animals
    '犬': 'dog',
    '猫': 'cat',
    'ウサギ': 'rabbit',
    'ライオン': 'lion',
    'ペンギン': 'penguin',
    '鶴': 'crane',
    '蛇': 'snake',
    'カメ': 'turtle',
    'イルカ': 'dolphin',
    'アザラシ': 'seal',
    '蝶': 'butterfly',
    'カブトムシ': 'rhinoceros beetle'
}

def translate_to_english(japanese_input):
    return translation_dict.get(japanese_input, japanese_input)

def create_prompt(character_type, character_traits, appearance_attributes=None, special_features=None, animal_attributes=None):
    prompt = "high quality, deatailed, beautiful, anime style, face and upper body of a" + translate_to_english(character_type)

    if character_traits:
        traits_in_english = [translate_to_english(trait) for trait in character_traits]
        prompt += " who is " + " and ".join(traits_in_english)

    if character_type in ["男性", "女性"] and appearance_attributes:
        for key, value in appearance_attributes.items():
            prompt += f", with {translate_to_english(value)} {translate_to_english(key)}"
        for key, value in special_features.items():
            prompt += f", with {translate_to_english(value)}"

    elif character_type == "動物" and animal_attributes:
        for key, value in appearance_attributes.items():
            prompt += f", with {translate_to_english(key)}"

    return prompt
