import socket
import datetime

# Store client times
client_times = []

# Create socket
server = socket.socket()
server.bind(('localhost', 8080))
server.listen(5)

print("Server is running. Waiting for clients...")

# Accept 3 clients
for _ in range(3):
    conn, addr = server.accept()
    print(f"Connected to client: {addr}")
    
    data = conn.recv(1024).decode()
    now = datetime.datetime.now()
    client_time = datetime.datetime.strptime(data, "%H:%M:%S").replace(
        year=now.year, month=now.month, day=now.day
    )

    print(f"Received time from client {addr}: {client_time.time()}")
    
    client_times.append((conn, client_time))

# Get master's time
master_time = datetime.datetime.now().replace(microsecond=0)
print(f"\nMaster Time: {master_time.time()}")

# Calculate average time
all_times = [master_time] + [t[1] for t in client_times]
timestamps = [t.timestamp() for t in all_times]
average_timestamp = sum(timestamps) / len(timestamps)
average_time = datetime.datetime.fromtimestamp(average_timestamp)

print(f"\nAverage Time (synchronized time): {average_time.time()}")

# Send synchronized time to all clients
for conn, t in client_times:
    conn.send(average_time.strftime("%H:%M:%S").encode())
    conn.close()

server.close()
