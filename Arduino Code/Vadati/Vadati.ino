#include "NewPing.h"
#define TRIGGER_PIN 9
#define ECHO_PIN 10
int flex1 = A0; // flex sensor is connected with analog pin A0
int flex2=A1;
int flex3=A2;
int data1 = 0; 
int data2=0;
int data3=0;
//int trigPin=9;
//int echoPin=10;
NewPing sonar(TRIGGER_PIN, ECHO_PIN, 1500);
float duration, distance;

void setup()
{
  Serial.begin(9600); 
  pinMode(flex1, INPUT);
  pinMode(flex2, INPUT);
  pinMode(flex3, INPUT);
  

}

void loop()
{
  
 
  distance = sonar.ping_cm();
  
  data1 = analogRead(flex1);
  data2=analogRead(flex2);
  data3=analogRead(flex3); 
  Serial.print(data1);
  Serial.print("f");
  Serial.print(data2);
  Serial.print("f");
  Serial.print(data3); 
  Serial.print("x");
  Serial.print(distance); 
  Serial.println();
  
  
  delay(900); 
}
