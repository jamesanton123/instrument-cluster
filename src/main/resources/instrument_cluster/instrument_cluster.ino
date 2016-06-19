//to burn this program, make sure to remove wire number 0(rx)
String commandMessage = "";
int input = 0;

int lTurn1 = 0;
int lTurn2 = 0;
int lTurn3 = 0;
int rTurn1 = 0;
int rTurn2 = 0;
int rTurn3 = 0;

void setup() {  
    Serial.begin(57600);    
    pinMode(12, INPUT);
    pinMode(11, INPUT);
    pinMode(10, INPUT);
    pinMode(9, INPUT);
    pinMode(8, INPUT);
    pinMode(7, INPUT);
    pinMode(6, INPUT);
    pinMode(5, INPUT);
    pinMode(4, INPUT);
    pinMode(3, INPUT);
}

void loop(){ 
    // If there is a byte ready to read
    if(Serial.available() > 0){
        commandMessage = Serial.readStringUntil('z');
        processInformation();
    }
}

void processInformation(){
    if(commandMessage.equals("ana0")){
        Serial.print(String(analogRead(A0)) + "#");
    }else if(commandMessage.equals("ana1")){
        Serial.print(String(analogRead(A1)) + "#");
    }else if(commandMessage.equals("ana2")){
        Serial.print(String(analogRead(A2)) + "#");
    }else if(commandMessage.equals("ana3")){
        Serial.print(String(analogRead(A3)) + "#");
    }else if(commandMessage.equals("ana4")){
        Serial.print(String(analogRead(A4)) + "#");
    }else if(commandMessage.equals("ana5")){
        Serial.print(String(analogRead(A5)) + "#");
    }else if(commandMessage.equals("dig3")){
        Serial.print(String(digitalRead(3)) + "#");
    }else if(commandMessage.equals("dig4")){
        Serial.print(String(digitalRead(4)) + "#");
    }else if(commandMessage.equals("dig5")){
        Serial.print(String(digitalRead(5)) + "#");
    }else if(commandMessage.equals("dig6")){
        Serial.print(String(digitalRead(6)) + "#");
    }else if(commandMessage.equals("dig7")){
        Serial.print(String(digitalRead(7)) + "#");
    }else if(commandMessage.equals("dig8")){
        Serial.print(String(digitalRead(8)) + "#");
    }else if(commandMessage.equals("dig9")){
        Serial.print(String(digitalRead(9)) + "#");
    }else if(commandMessage.equals("dig10")){
        Serial.print(String(digitalRead(10)) + "#");
    }else if(commandMessage.equals("dig11")){
        rTurn1 = digitalRead(11);
		delay(5);
		rTurn2 = digitalRead(11);
		delay(5);
		rTurn3 = digitalRead(11);
		if(rTurn1 == 1 && rTurn2 == 1 && rTurn3 == 1){
			Serial.print("1#");
		}else{
			Serial.print("0#");
		}
    }else if(commandMessage.equals("dig12")){
		lTurn1 = digitalRead(12);
		delay(5);
		lTurn2 = digitalRead(12);
		delay(5);
		lTurn3 = digitalRead(12);
		if(lTurn1 == 1 && lTurn2 == 1 && lTurn3 == 1){
			Serial.print("1#");
		}else{
			Serial.print("0#");
		}		
    }else{
		Serial.print("bad1#");
	}
}

