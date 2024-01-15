docker run -p 80:8080 \
--name chatgpt-api-1.5 \
-e PARAMS="
    --sever.port=80
    --chatgpt.host=https://api.openai.com/
    --chatgpt.apiKey=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
    --wx.config.originalid=gh_e851d0f4790c
    --wx.config.appid=wx8a87b517b5051ac6
    --wx.config.gatewayAddress=b8b6" \
-d sunyu/chatgpt-api:24-01-15