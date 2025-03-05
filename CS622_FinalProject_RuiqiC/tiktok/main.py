from TikTokApi import TikTokApi

api = TikTokApi()
count = 1
name = "funny"
for video in api.hashtag(name).videos(count):
    video_bytes = video.bytes()
    print(video_bytes.decode())
    break
with open("test.mp4", "wb") as out:
    out.write(video_bytes)
