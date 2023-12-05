$(document).ready(function() {
    const url = '/hotels';
    const settings = {
      method: 'GET',
    };
    
    fetch(url, settings)
      .then(response => response.json())
      .then(data => {
        $.each(data, (i, hotel) => {
          let deleteButton = '<button ' +
            'id=' + '\"' + 'btn_delete_' + hotel.id + '\"' +
            ' type="button" class="btn btn-danger btn_delete" ' + 
            'data-toggle="modal" ' +
            'data-target="#delete-modal">' +
            '&times</button>';
          
          let get_More_Info_Btn = '<button ' +
            'id=' + '\"' + 'btn_id_' + hotel.id + '\"' +
            ' type="button" class="btn btn-info btn_id">' +
            hotel.id +
            '</button>';
          
          let tr_id = 'tr_' + hotel.id;
          let hotelRow = '<tr id=\"' + tr_id + "\"" + '>' +
            '<td>' + get_More_Info_Btn + '</td>' +
            '<td class=\"td_name\">' + hotel.name.toUpperCase() + '</td>' +
            '<td class=\"td_category\">' + hotel.category + '</td>' +
            '<td>' + deleteButton + '</td>' +
            '</tr>';
          
          $('#hotelTable tbody').append(hotelRow);
        });
      });
  });
  