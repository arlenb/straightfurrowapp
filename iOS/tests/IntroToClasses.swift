print("Intro to Classes!\n")

class Customer{
    var name: String!
    var id = -1
    var address: String?
    var phone: String?
}

var someCust = Customer()

someCust.id = 1
someCust.name = "John Doe"
someCust.address = "1234 Elm Street\nTownsville, IA 55555"
someCust.phone = "515.297.3801"

print("Name   : \(someCust.name)")
print("id     : \(someCust.id)")
if let addr = someCust.address{
    print("address: \(someCust.address!)")
}
if let phone = someCust.phone{
    print("phone  : \(someCust.phone!)")
}