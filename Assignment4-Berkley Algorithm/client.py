import socket
import datetime
import random
import time

# Simulate clock offset
offset_seconds = random.randint(-5, 5)
client_time = datetime.datetime.now() + datetime.timedelta(seconds=offset_seconds)
client_time = client_time.replace(microsecond=0)

print(f"My time before sync: {client_time.time()}")

# Connect to server
client = socket.socket()
client.connect(('localhost', 8080))

# Send current time to server
client.send(client_time.strftime("%H:%M:%S").encode())

# Receive synchronized time from server
data = client.recv(1024).decode()
synced_time = datetime.datetime.strptime(data, "%H:%M:%S")
print(f"Synchronized time: {synced_time.time()}")

client.close()
