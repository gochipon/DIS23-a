import torch
import torchvision.transforms as transforms
from PIL import Image

# U-2-Netモデルの定義
from u2net_model import U2NET # あなたはこのモデルを正しくインポートする必要があります。

def remove_bg_u2net(model_path, input_image_path):
    # モデルのロード
    net = U2NET(3, 1)
    net.load_state_dict(torch.load(model_path))
    net.eval()

    # 画像の変換
    transform = transforms.Compose([
        transforms.Resize((320, 320)),
        transforms.ToTensor()
    ])

    img = Image.open(input_image_path).convert('RGB')
    img = transform(img)
    img = img.unsqueeze(0)

    # 予測
    with torch.no_grad():
        prediction = net(img)

    # 予測結果をマスクとして使用
    mask = prediction.squeeze().cpu().numpy()
    mask[mask > 0.5] = 1
    mask[mask <= 0.5] = 0

    original = Image.open(input_image_path).convert("RGBA")
    for i in range(original.size[0]):
        for j in range(original.size[1]):
            if mask[j][i] == 0:
                original.putpixel((i,j), (255,255,255,0))

    return original

# 使用例:
model_path = 'path_to_pretrained_u2net.pth'
input_image_path = 'path_to_input_image.jpg'
result = remove_bg_u2net(model_path, input_image_path)
result.show()
