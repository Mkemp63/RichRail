# RichRail

RichRail Project voor PAFR 2017

## Commands
```
new train tr1; // response is “train tr1 created”
new wagon 1111; // response is “wagon 1111 created with 20 seats”
new wagon 2222 numseats 15; // response is “wagon 2222 created with 15 seats”
add 1111 to tr1; // response: “wagon 1111 added to train tr1”
getnumseats train tr1; // response: “number of seats in train tr1: 20”
getnumseats wagon 2222; // response: “number of seats in wagon 2222: 15”
delete train tr1; // response: “train tr1 deleted”
delete train tr2; // response: “train tr2 does not exist”
remove 1111 from tr1; // response: “wagon wg1 removed from train tr1”
```
