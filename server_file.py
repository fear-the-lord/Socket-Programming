import socket 
import threading 

PORT = 5050
SERVER = socket.gethostbyname(socket.gethostname())
ADDRESS = (SERVER, PORT)

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind(ADDRESS)

def handle_client(conn, addr):
    print(f"[NEW CONNECTION] {addr} connected.")
    filename = input(str("Enter the filename: "))
    file = open(filename, 'rb')
    file_data = file.read(2048)
    conn.send(file_data)
    print(f"Data transmitted successfully to client [{addr}]")

def start():
    server.listen()
    print(f"[LISTENING] Server is listening on {SERVER}")
    while True:
        conn, addr = server.accept()
        thread = threading.Thread(target=handle_client, args=(conn, addr))
        thread.start()
        print(f"[ACTIVE CONNECTIONS] {threading.activeCount() - 1}")


print("[STARTING] Server is starting...")
start()
