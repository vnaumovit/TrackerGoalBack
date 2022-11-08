async function editItem(modal, id) {
  let request = await itemFetch.getItemById(id);
  let item = request.json();
  fillItemModal(modal);
  editItemForm(item, modal);
  let button = '#editItemButton';
  validation(button);
  $(button).on('click', async () => {
    const data = await itemData(modal);
    const itemResponse = await itemFetch.updateItem(data);
    sizes = [];
    if (itemResponse.ok) {
      await getItems();
      modal.modal('hide');
    }
  })
}

function editItemForm(item, modal) {
  item.then(item => {
    let bodyForm = `
            <form class="form-group text-center needs-validation" id="editItem" novalidate>
               <div hidden class="form-group">
                    <label for="id" class="col-form-label">ID</label>
                    <input type="text" class="form-control" id="id" value="${item.id}" readonly>
               </div>
            
                <div class="form-group">
                    <label for="name" class="col-form-label">Название</label>
                    <input type="text" class="form-control" id="name" value="${item.name}" required>
                    <div class="invalid-feedback" id="invalidName">Введите имя товара</div>
                    <div class="valid-feedback">Все хорошо!</div>
                </div>  
                          
                <div class="form-group">
                    <label for="code" class="col-form-label">Код</label>
                    <input type="text" class="form-control" id="code" value="${item.code}" required readonly>
                </div>
        
                <div class="form-group">
                    <label for="itemType" class="label-select">Тип</label>
                    <select id="itemType" class="form-control">
                        <option value="Кольцо">Кольцо</option>
                        <option value="Браслет">Браслет</option>
                        <option value="Колье">Колье</option>
                        <option value="Серьга">Серьга</option>
                        <option value="Шарм">Шарм</option>
                    </select required>
                    <div class="invalid-feedback" id="invalidItemType">Выберите тип объекта</div>
                    <div class="valid-feedback">Все хорошо!</div>
                </div>
        
                <div class="form-group">
                    <label for="retailPrice" class="com-form-label">Продажная цена</label>
                    <input type="number" class="form-control" id="retailPrice" value="${item.retailPrice}" min="0" required> 
                    <div class="invalid-feedback" id="invalidRetailPrice">Цена не может быть меньше 0</div>
                    <div class="valid-feedback">Все хорошо!</div>
                </div>
                        
                <div class="form-group">
                    <button type="button" class="btn btn-info" id="editSize" data-toggle="modal"
                     data-item-id="${item.id}" data-action="editSize" onclick="showSizeModal(this)">Изменить размеры</button>
                    <div class="invalid-feedback" id="invalidItemQuantity">Добавьте размеры</div>
                    <div class="valid-feedback">Все хорошо!</div>
                </div>
        
                <div class="form-group marginTop">
                    <label for="image" >Фото:</label>
                    <input type="file" class="form-control" id="image" name="image" required>
                    <div class="invalid-feedback" id="invalidImage">Выберите изображение</div>
                    <div class="valid-feedback">Все хорошо!</div>
                </div>
            </form>
        `;
    modal.find('.modal-body-item').append(bodyForm);
    modal.find('#itemType').val(item.itemType)
  });
}
