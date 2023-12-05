function find(id) {

    const url = '/hotels/' + id;
    const settings = {
    method: 'GET'
    }
    fetch(url,settings).then(response => response.json())
    .then(data => {
    let hotel = data;
    $("#hotel_id").val(hotel.id);
    $("#name").val(hotel.name);
    $("#category").val(hotel.category);
    $("#div_hotel_updating").css({"display": "block"});
    }).catch(error => {
    console.log(error);
    alert("Error -> " + error);
    })
    }