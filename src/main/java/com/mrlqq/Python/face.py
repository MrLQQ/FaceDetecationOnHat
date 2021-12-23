import cv2
import random
import sys

strs = sys.argv[1:]
print(strs)

#定义一个上传文件的路径
basePaht = "E:/imageUpload/"
# basePaht = "/home/FaceDetectionOnHat/imageUpload/"

img_path = basePaht+strs[0]
print(img_path)

# OpenCV 人脸检测
face_patterns_people = cv2.CascadeClassifier('haarcascades/haarcascade_frontalface_alt.xml')

sample_image = cv2.imread(img_path)
# cv2.namedWindow("before",cv2.WINDOW_NORMAL)
# cv2.resizeWindow("before", 640, 480)
# cv2.imshow("before", sample_image)
faces_people = face_patterns_people.detectMultiScale(sample_image, scaleFactor=1.2, minNeighbors=3)
# faces_anime = face_patterns_dm.detectMultiScale(sample_image, scaleFactor=1.15, minNeighbors=2)


# 圣诞帽
hats = []
for i in range(5):
    hats.append(cv2.imread('src/main/java/com/mrlqq/Python/img/hat%d.png' % i, -1))

def showFace(faces):
    for x, y, w, h in faces:
        cv2.rectangle(sample_image, (x, y), (x+w, y+h), (0, 255, 0), 2)


def inHat(faces):
    for face in faces:
#         showFace(faces)
        # 随机一顶帽子
        hat = random.choice(hats)
        # 调整帽子尺寸
        scale = face[2] / hat.shape[0]*1.33
        hat = cv2.resize(hat, (0, 0), fx=scale, fy=scale)

#         cv2.imshow("hat", hat)
        # 根据人脸坐标调整帽子位置
        x_offset = int(face[0] + face[2] / 2 - hat.shape[1] / 2)
        y_offset = int(face[1] - hat.shape[0]/2.5)
#         print("%d,%d",x_offset,y_offset)
        # 计算贴图位置
        x1, x2 = max(x_offset, 0), min(x_offset + hat.shape[1], sample_image.shape[1])
        y1, y2 = max(y_offset, 0), min(y_offset + hat.shape[0], sample_image.shape[0])
        hat_x1 = max(0, -x_offset)
        hat_x2 = hat_x1 + x2 - x1
        hat_y1 = max(0, -y_offset)
        hat_y2 = hat_y1 + y2 - y1

#         print("-=-=-=-=-=-=-=-=")
#         print(hat_x1,hat_y1)
#         print(hat_x2,hat_y2)
#         print(x1,y1)
#         print(x2,y2)

        # 透明部分的处理
        alpha_h = hat[hat_y1:hat_y2, hat_x1:hat_x2, 3] / 255
        alpha = 1 - alpha_h

#         cv2.imshow("alpha_h", alpha_h)
#         cv2.imshow("alpha", alpha)
        # 按3个通道合并图片
        for c in range(0, 3):
            sample_image[y1:y2, x1:x2, c] = (alpha_h * hat[hat_y1:hat_y2, hat_x1:hat_x2, c] + alpha * sample_image[y1:y2, x1:x2, c])

#showFace(faces_people)
#showFace(faces_anime)
inHat(faces_people)
#inHat(faces_anime)

# 显示最终结果
nameSplit = strs[0].split(".")
newImgName = nameSplit[0]+"_hat."+nameSplit[1]
print(newImgName)
cv2.imwrite(basePaht+newImgName,sample_image)

print(newImgName)

