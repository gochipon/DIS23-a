import requests
import json
from PIL import Image
from io import BytesIO

# Flask APIのURL（デフォルトのホストとポートを使用）
url = "http://127.0.0.1:5000/generate"

# テストデータの作成
test_data = {
    "character_type": "女性",
    "character_traits": ["ポジティブ", "ユーモラス"],
    "appearance_attributes": {
        "人種": "日本人",
        "髪の色": "ブロンド",
        "髪のスタイル": "カール",
        "服装スタイル": "エレガント",
    },
    "special_features": {
        "アクセサリー": "イヤリング"
    },
    "animal_attributes": {}
}

# APIの呼び出し
response = requests.post(url, json=test_data)

# 応答の表示
response_data = response.json()
print(f"Generated Image URL: {response_data['image_url']}")

# さらに、応答された画像URLから画像を取得して表示することもできます（必要に応じて）:
response_img = requests.get(response_data['image_url'])
img = Image.open(BytesIO(response_img.content))
img.show()

