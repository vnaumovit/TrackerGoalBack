function clearSizes() {
  sizes = [];
  $('#sizeCount').val('')
}

function filterSizes(sizes) {
  let resArr = [];
  sizes.filter(function (size) {
    let i = resArr.findIndex(x => x.size === size.size);
    if (i <= -1) {
      resArr.push({ id: size.id, size: size.size, quantity: size.quantity });
    }
    return null;
  });
  return resArr;
}


async function fillOldSizes(modal) {
  let sizeCount = $('#sizeCount');
  let checkSizes = sizeCount.val() === '' && sizes.length === 0;
  if (checkSizes) {
    await addSizeForm(modal, 0);
    sizeCount.val(1)
  } else {
    let sizesForm = await sizesForeach(sizes, 0);
    let form = document.querySelector('#addSizeForm');
    modal.find(form).append(sizesForm);
  }
}

async function addOrDeleteSize(modal) {
  let currentCount = $('#sizeCount').val()
  $('#addElseSize').click(async () => {
    currentCount = await addSizeForm(modal, currentCount);
    $("#sizeCount").val(currentCount)
  })
  $('#deleteSizeButton').click(async () => {
    currentCount = await deleteSizeForm(currentCount);
    $("#sizeCount").val(currentCount)
  });
}

function addSizeBaseForm(modal) {
  let baseForm = `
      <form class="form-group text-center needs-validation" id="addSizeForm" novalidate>
      </form>
      <input id="sizeCount" hidden>`;
  modal.find('.modal-body-optional').append(baseForm);
}

async function deleteSizeForm(count) {
  let currCount = count
  if (count > 0) {
    let parent = document.getElementById('addSizeForm');
    let child = document.getElementById('sizeForm' + count);
    parent.removeChild(child)
    if (sizes.length !== 0) {
      sizes.pop()
    }
    currCount--;
  }
  return currCount;
}

async function addSizeForm(modal, count) {
  let currCount = count
  if (count <= 5) {
    currCount++;
    let bodyForm = `
              <div class="form-floating mb-3" id="sizeForm${currCount}">
                  <label for="size${currCount}">Размер ${currCount}</label>
                  <input class="form-control" id="size${currCount}" required min="0">
                  <label for="quantity${currCount}">Количество ${currCount} размера</label>
                  <input class="form-control" id="quantity${currCount}" required min="0">
                  <div class="invalid-feedback">Добавьте размеры и количество</div>
                  <div class="valid-feedback">Все хорошо!</div>
              </div>
        `;
    let form = document.querySelector('#addSizeForm');
    modal.find(form).append(bodyForm);
  }
  return currCount;
}
