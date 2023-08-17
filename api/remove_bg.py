import cv2
import numpy as np

def remove_background(image_path, output_path):
    # 画像を読み込む
    img = cv2.imread(image_path)
    
    # 初期マスクを作成する
    mask = np.zeros(img.shape[:2], np.uint8)
    
    # 背景モデルと前景モデルを初期化する
    bgd_model = np.zeros((1, 65), np.float64)
    fgd_model = np.zeros((1, 65), np.float64)
    
    # 画像の領域を定義する
    rect = (10, 10, img.shape[1]-10, img.shape[0]-10) # 画像のほぼ全領域を指定します
    
    # GrabCutアルゴリズムを実行する
    cv2.grabCut(img, mask, rect, bgd_model, fgd_model, 5, cv2.GC_INIT_WITH_RECT)
    
    # 新しいマスクを作成する
    mask2 = np.where((mask == 2) | (mask == 0), 0, 1).astype('uint8')
    
    # 画像に新しいマスクを乗算して背景を除去する
    img = img * mask2[:, :, np.newaxis]
    
    # 出力画像を保存する
    cv2.imwrite(output_path, img)

# 使用例
remove_background('input_image.jpg', 'output_no_bg.png')
