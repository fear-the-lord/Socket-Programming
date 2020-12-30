# CLIENT PROGRAM 

# Import the necessary dependencies
import socket 

HEADER = 64
PORT = 5050
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = "DISCONNECT"
SERVER = socket.gethostbyname(socket.gethostname())
# Bind the client address
ADDRESS = (SERVER, PORT)

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(ADDRESS)

# Define a function to send message to the server
def send(msg):
    message = msg.encode(FORMAT)
    msg_length = len(message)
    send_length = str(msg_length).encode(FORMAT)
    send_length += b' ' * (HEADER - len(send_length))
    # Send the message length separately to the server
    client.send(send_length)
    # Send the message to the server
    client.send(message)
    print(f"Server: {client.recv(1024).decode(FORMAT)}")

msg_client = input(str("\t\t\tYou: "))
while msg_client != DISCONNECT_MESSAGE:
	send(msg_client) 
	msg_client = input(str("\t\t\tYou: "))

send(DISCONNECT_MESSAGE)
	

