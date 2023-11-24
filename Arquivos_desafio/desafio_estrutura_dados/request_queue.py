import requests
r = requests.post("http://127.0.0.1:5000/start_processing")
print(r.status_code)