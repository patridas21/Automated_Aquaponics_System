#include <DHTesp.h>
#include <FirebaseESP8266.h>
#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>
#include <DallasTemperature.h>
#include <OneWire.h>

#define SOLENOID1 12
#define SOLENOID2 13
#define SOLENOID3 14
#define DHT_PIN 15
#define ONE_WIRE_BUS 4
#define BASILMOIST A0
#define LETTUCEMOIST A1
#define ONIONMOIST A2
#define FIREBASE_HOST "https://aquadb-54df6.firebaseio.com"
#define FIREBASE_AUTH "moruyim2VRiJ4CYdx0iqwc7gtXnkAECy0AttIJk3"

OneWire oneWire(ONE_WIRE_BUS);
DallasTemperature sensors(&oneWire);
FirebaseData firebasedata;
DHTesp DHT;

int i=0; 
int dry= 1023;
int wet= 439;
double basil_moist=0;
double lettuce_moist=0;
double onion_moist=0;
double air_temp=0;
double air_humidity=0;
double water_ph=0;
double water_temp=0;
unsigned long current_time=0;
unsigned long last_db_update=0;
void setup(void)
{
  Serial.begin(9600);
  sensors.begin();
  pinMode(DHT_PIN, INPUT);//Define dht pin type as input
  pinMode(SOLENOID, OUTPUT);// Define Solenoid pin as output
  DHT.setup(DHT_PIN, DHTesp::DHT11);
// connect to wifi. 
  //WiFi.begin("CYTACD1E","ZTEEF4AF7M05361");
  WiFi.begin("COSMOTE-05495C","2662024949");
  Serial.print("connecting"); 
  while (WiFi.status() != WL_CONNECTED) { 
     Serial.print("."); 
     delay(500); 
  } 
  
  Serial.println(); 
  Serial.print("connected: "); 
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}

int n=0;
void loop(void)
{
  
   //Ambient Temperature and Humodity
    air_temp = DHT.getTemperature();
    air_humidity = DHT.getHumidity();
    Serial.print("Temperature:");
    Serial.println(DHT.getTemperature());
    Serial.print("Humidity:");
    Serial.println(DHT.getHumidity());
    delay(500);
    
    //Water Temprature
    sensors.requestTemperatures(); 
    water_temp=sensors.getTempCByIndex(0);// Get Temperature in Celcius
    Serial.print("Water Temperature:");
    Serial.println(water_temp);

    //Soil Moisture
    basil_moist=analogRead(BASILMOIST);
    basil_moist=map(basil_moist, dry, wet, 0, 100);// Soil Moisture Sensor Calibration
    Serial.print("Soil Moisture:");
    Serial.print(basil_moist);
    delay(500);
   //Solenoid
    if (basil_moist<=50){
      digitalWrite(SOLENOID1, LOW);// Open solenoid
      Serial.println("soilmoist low");
    }
    else if (basil_moist>=70){
      digitalWrite(SOLENOID1, HIGH); // Close solenoid 
      Serial.println("soilmoist high");
    }
    
    //Soil Moisture
    lettuce_moist=analogRead(LETTUCEMOIST);
    lettuce_moist=map(lettuce_moist, dry, wet, 0, 100);// Soil Moisture Sensor Calibration
    Serial.print("Soil Moisture:");
    Serial.print(lettuce_moist);
    delay(500);
   //Solenoid
    if (lettuce_moist<=50){
      digitalWrite(SOLENOID2, LOW);// Open solenoid
      Serial.println("soilmoist low");
    }
    else if (lettuce_moist>=70){
      digitalWrite(SOLENOID2, HIGH); // Close solenoid 
      Serial.println("soilmoist high");
    }
    //Soil Moisture
    onion_moist=analogRead(ONIONMOIST);
    onion_moist=map(onion_moist, dry, wet, 0, 100);// Soil Moisture Sensor Calibration
    Serial.print("Soil Moisture:");
    Serial.print(soil_moist);
    delay(500);
   //Solenoid
    if (onion_moist<=50){
      digitalWrite(SOLENOID3, LOW);// Open solenoid
      Serial.println("soilmoist low");
    }
    else if (onion_moist>=70){
      digitalWrite(SOLENOID3, HIGH); // Close solenoid 
      Serial.println("soilmoist high");
    }
    
    //Convert all sensor readings to String
    String airtemp = String(air_temp) + String("C"); 
    String airhumidity = String(air_humidity) + String("%"); 
    String soilmoist1 = String(basil_moist) + String("%");  
    String soilmoist2 = String(lettuce_moist) + String("%"); 
    String soilmoist3 = String(onion_moist) + String("%");  
    String watertemp = String(water_temp) + String("C");  
    current_time = millis(); 
    
 // if( (last_db_update == 0) || ((current_time-last_db_update) >= 86400000) ){//Push to database once a day
    
    Serial.print("PUSHING TO FIRABASE :Temperature = ");
    Serial.println(air_temp);
    Serial.print("PUSHING TO FIRABASE :Humidity = ");
    Serial.println(air_humidity);
    Serial.print("PUSHING TO FIRABASE : SOIL MOISTURE = ");
    Serial.println(basil_moist);
    Serial.print("PUSHING TO FIRABASE : SOIL MOISTURE = ");
    Serial.println(lettuce_moist);
    Serial.print("PUSHING TO FIRABASE : SOIL MOISTURE = ");
    Serial.println(onion_moist);
    Serial.print("PUSHING TO FIRABASE : Water Temperature = ");
    Serial.println(water_temp);
    Serial.print("PUSHING TO FIRABASE : Water PH = ");
    //Serial.println(water_ph);
    //Push to Firebase Table for Current Readings
    
    Firebase.setString(firebasedata, "/metrics2/air_temp", airtemp);
    Firebase.setString(firebasedata, "/metrics2/air_humidity", airhumidity);
    Firebase.setString(firebasedata, "/metrics2/basil_moist", soilmoist1);
    Firebase.setString(firebasedata, "/metrics2/lettuce_moist", soilmoist2);
    Firebase.setString(firebasedata, "/metrics2/onion_moist", soilmoist3);
    Firebase.setString(firebasedata, "/metrics2/water_temp", watertemp);

    i = (current_time / 86400000);//Keep the integer part that shows the days past from the start
    // We have only 10 tables on the database
  //  if (i>9){ 
  //    i=i%10;
  //  }
    i=1;
    //Push to Firebase Tables for History Readings
    String k = String(i);
    Firebase.setString(firebasedata, "/metrics2/"+k+"/air_temp", airtemp);
    Firebase.setString(firebasedata, "/metrics2/"+k+"/air_humidity", airhumidity);
    Firebase.setString(firebasedata, "/metrics2/"+k+"/basil_moist", soilmoist1);
    Firebase.setString(firebasedata, "/metrics2/"+k+"/lettuce_moist", soilmoist2);
    Firebase.setString(firebasedata, "/metrics2/"+k+"/onion_moist", soilmoist3);
    Firebase.setString(firebasedata, "/metrics2/"+k+"/water_temp", watertemp);
    last_db_update=millis();
  //}
 delay(10000);
}
