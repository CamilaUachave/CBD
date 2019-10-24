prefixo = function () {
 var total = db.phones.aggregate(
 [{
  $group : 
     {
     _id : "$components.prefix",
     total: { $sum : 1}
     }
 }]
 )
  
 while (total.hasNext()){
 	print("Total de "+total.next()._id+" = "+total.next().total);
}
}

 
