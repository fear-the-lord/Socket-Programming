# SERVER PROGRAM

# Import all the necessary dependencies
import socket 
import threading 

# The port should be same as the client port
PORT = 5050
# Get the server host IP
SERVER = socket.gethostbyname(socket.gethostname())
ADDRESS = (SERVER, PORT)

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# Bind the address of the server
server.bind(ADDRESS)

# Define a function that transmits the file from the server to the client
def handle_client(conn, addr):
    print(f"[NEW CONNECTION] {addr} connected.")
    # Receive the name of the file to be transmitted from the user
    filename = input(str("Enter the filename: "))
    # Open the file in read-only mode
    file = open(filename, 'rb')
    # Read data from the file
    file_data = file.read(2048)
    # Send the data to the client
    conn.send(file_data)
    print(f"Data transmitted successfully to client [{addr}]")

# Define a function that starts the server and waits for client connections
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
