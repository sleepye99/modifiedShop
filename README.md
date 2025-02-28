# modifiedShop
improved version of the shop. the original version of the project has renewed in accordance with clean architecture and layered architecture principles.
when adding an item, now using /items endpoint is enough. just add the categoryId of the item in the JSON body. For example:
{
    "name": "Deneme Ürünü",
    "price": 1,
    "stockQuantity": 2,
    "categoryId": 5
}
implemented new features such as bringing items by its category id, get specific category with given category id etc.
