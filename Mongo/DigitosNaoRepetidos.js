DigitosNaoRepetidos = function () {
    var numero = db.phones.find({},{"components.number":1}); 
    while (numero.hasNext()){
       print("numero: "+numero.next().components.number);
    }
}
