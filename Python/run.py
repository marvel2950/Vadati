import serial
import time
import schedule
import requests


def main_func():
    res=''
    arduino = serial.Serial('com4', 9600)
    print(' connected')
    arduino_data = arduino.readline()
    dis='0'

    decoded_values = str(arduino_data[0:len(arduino_data)].decode("utf-8"))
    list_values = decoded_values.split('x')
    dis=list_values[1]
    list_values=list_values[0].split('f')

    for i in list_values:
        list_in_floats.append(int(i))
    if((list_in_floats[0]<=1006 and list_in_floats[0]>=1004) and (list_in_floats[1]<=1006 and list_in_floats[1]>=1004) and (list_in_floats[2]<=1006 and list_in_floats[2]>=1004)):
        res='Hi'
    elif(list_in_floats[0]<1004 and list_in_floats[1]<1004 and list_in_floats[2]<1004):
        res='Punch'
    elif(list_in_floats[0]>1006 and list_in_floats[1]>1006 and list_in_floats[2]>1006):
        res='Bye'
    else:
        res='Others'
    temp='''if(dis=='0.00'):
        myobj = {'data': res}
        x = requests.post(url, data = myobj)
        print("Input was "+x.text)
    else:
        myobj = {'data': res,'distance':dis}
        x = requests.post(url, data = myobj)
        print("Input was "+x.text)'''
    myobj = {'data': res,'distance':dis}
    x = requests.post(url, data = myobj)
    print("Input was "+x.text)

    print(f'Readings from Arduino: {list_in_floats}')
    print("Distance is "+dis)

    arduino_data = 0
    list_in_floats.clear()
    list_values.clear()
    arduino.close()
    print('Connection closed')
    


list_values = []
list_in_floats = []
url = 'http://vadati.scienceontheweb.net/addmess.php/post'

print('Program started')

# Setting up the Arduino
schedule.every(2).seconds.do(main_func)

while True:
    schedule.run_pending()
    time.sleep(0.9)
