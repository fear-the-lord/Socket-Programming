import socket

HEADER = 64
PORT = 5050
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = "DISCONNECT"
SERVER = socket.gethostbyname(socket.gethostname())
ADDRESS = (SERVER, PORT)

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(ADDRESS)

filename = input(str("Enter the receiving filename: "))
file = open(filename, 'wb')
file_data = client.recv(2048)
file.write(file_data)
file.close()
print("File received successfully from server")