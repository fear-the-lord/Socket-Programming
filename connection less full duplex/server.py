# SERVER PROGRAM 

# Import all the necessary dependencies
import socket 
import threading

HEADER = 64
PORT = 5050
SERVER = socket.gethostbyname(socket.gethostname())
ADDRESS = (SERVER, PORT)
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = "DISCONNECT"

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# Bind the server address
server.bind(ADDRESS)

# Define the function that handles the client
def handle_client(conn, addr):
    print(f"[NEW CONNECTION] {addr} connected.")

    connected = True
    # Loop until the client remains connected
    while connected:
        msg_length = conn.recv(HEADER).decode(FORMAT)
        if msg_length:
            msg_length = int(msg_length)
            # Receive the message from the client
            msg = conn.recv(msg_length).decode(FORMAT)
            print(f"[{addr}] {msg}")
            # Check if the message is an exit message
            if msg == DISCONNECT_MESSAGE:
                print(f"[{addr}] has disconnected from the Server")
                print("\n")
                # Count the number of active clients on the server
                print(f"[ACTIVE CONNECTIONS] {(threading.activeCount() - 1)- 1}")
                connected = False

            if msg != DISCONNECT_MESSAGE: 
                conn.send("Message Received".encode(FORMAT))
            else:
                conn.send("Thank You for chatting with us!!".encode(FORMAT))


    conn.close()
        

# Define a function to start the server
def start():
    server.listen()
    print(f"[LISTENING] Server is listening on {SERVER}")
    while True:
        conn, addr = server.accept()
        thread = threading.Thread(target=handle_client, args=(conn, addr))
        thread.start()
        print(f"[ACTIVE CONNECTIONS] {threading.activeCount() - 1}")


print("[STARTING] Server is starting...")
# Start the server
start()

