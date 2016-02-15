let dataStr = "[{\"id\":\"1\",\"owner_id\":\"1\",\"name\":\"Customer 1\",\"address\":\"5555 S 145 Ave\r\nStory City, IA 50248\"},{\"id\":\"2\",\"owner_id\":\"1\",\"name\":\"Favorite Client\",\"address\":\"1234 East Lane\r\nJewell, IA 50130\"}]"

print("Data: \(dataStr)")

func parseData(){
    
    var namesArray = [String]()
    
    do {
        let json = try NSJSONSerialization.JSONObjectWithData(dataStr, options: .AllowFragments)
     
        if let customersData = json as? [[String: AnyObject]] {
            for custData in customersData {
				//parse out the name
                if let name = custData["name"] as? String {
                    namesArray.append(name)
                }
            }
        }
    } catch {
        print("error serializing JSON: \(error)")
    }
     
    print(namesArray) // should print names of customers parsed from the data at the top.
}