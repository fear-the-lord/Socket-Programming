# CLIENT PROGRAM 

# Import all the necessary dependencies
import socket

# Fix the header size and the port
HEADER = 64 
PORT = 5050
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = "DISCONNECT"
SERVER = socket.gethostbyname(socket.gethostname())
# Bind the address of the client
ADDRESS = (SERVER, PORT)

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# Form the client connection
client.connect(ADDRESS)

# Enter the name of the file to be created at the client site
filename = input(str("Enter the receiving filename: "))
# Open the file
file = open(filename, 'wb')
# Copy the data from the server into the client
file_data = client.recv(2048)
# Write the data into the new file
file.write(file_data)
# Close the file
file.close()
print("File received successfully from server")