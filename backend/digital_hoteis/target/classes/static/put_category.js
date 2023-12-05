window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_hotel_form');
    formulario.addEventListener('submit', function (event) {
      event.preventDefault(); 
  
      const hotelId = document.querySelector('#hotel_id').value;
      const formData = {
        id: document.querySelector('#hotel_id').value,
        name: document.querySelector('#name').value,
        category: document.querySelector('#category').value
      };
  
      const url = '/hotels';
      const settings = {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      };
  
      fetch(url, settings)
        .then(response => response.json())
        .then(data => {
          let hotel = data;
          let successAlert = '<div class="alert alert-success alert-dismissible">' +
            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
            '<strong> pelicula = </strong> ' + data.toString() +
            '</div>';
  
          $("#tr_" + hotelId + " td.td_name").text(hotel.name.toUpperCase());
          $("#tr_" + hotelId + " td.td_category").text(hotel.category.toUpperCase());
          $("#response").empty();
          $("#response").append(successAlert);
          $("#response").css({ "display": "block" });
        })
        .catch(error => {
          let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
            '<strong> Error </strong></div>';
          $("#response").empty();
          $("#response").append(errorAlert);
          $("#response").css({ "display": "block" });
        });
    });
  });
  