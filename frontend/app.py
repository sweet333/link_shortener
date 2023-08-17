import streamlit as st
import os
import requests
import re


st.set_page_config(
    page_title="Hello world",
    #page_icon="chart_with_upwards_trend",
    layout="wide",
)

hide_streamlit_style = """
            <style>
            #MainMenu {visibility: hidden;}
            footer {visibility: hidden;}
            </style>
            """
st.markdown(hide_streamlit_style, unsafe_allow_html=True) 

backend_url = "http://localhost:8080"

def is_url(url):
    regex = re.compile(
        r'^https?://'  # Протокол (http:// или https://)
        r'([A-Za-z0-9.-]+)'  # Доменное имя (любые буквы, цифры, дефисы и точки)
        r'(:\d+)?'  # Опциональный номер порта (:порт)
        r'(/([^\s/?#]+/?)+)?'  # Путь (опциональный, может включать дефисы и слэши)
        r'(\?[^\s/?#]+)?'  # Опциональный запрос (набор параметров, начинается с знака вопроса)
        r'(#.+$)?'  # Опциональный якорь (хэш-фрагмент)
    )
    return re.match(regex, url) is not None

st.title("Сократитель ссылок")

url = st.text_input("Введите вашу ссылку")
   
if url:
    if is_url(url):
        data = {
            "url": url
        }

        try:
            response = requests.post(backend_url, json=data)

            if response.status_code == 200:
                data = response.json()

                st.write(f"Ссылка успешно была сокращена {data['urlLink']}")
            else:
                st.write(f"Произошла ошибка: {response.content}")
        except(Exception):
            st.write(f"Произошла ошибка, сервер не доступен!")
    else:
        st.write(f"Ссылка была введа некорретно!")
