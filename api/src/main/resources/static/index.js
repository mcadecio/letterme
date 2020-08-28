const listAll = async () => {
    const response = await fetch('/api/letters/listall');

    return response.json();
};
const handleImageUpload = event => {
    const files = event.target.files;
    const formData = new FormData();
    formData.append('myFile', files[0]);

    fetch('/api/letters/save', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
        })
        .catch(error => {
            console.error(error)
        })
};


const makeItPretty = (result, div) => {
    for (let [key, value] of Object.entries(result.result)) {
        let innerHTML = `
<h3>${key}</h3>
<ul>
${value.map(s => `<li><a href='/api/letters/get/${key}/${s}'>${s}</a></li>`).map(s => s.toString()).toString().replace(/,/g, '')}
</ul>`;
        let element = document.createElement('p');
        element.innerHTML = innerHTML;
        div.append(element);
    }
};

// document.querySelector('#fileUpload').addEventListener('change', event => {
//     handleImageUpload(event)
// });

document.getElementById('listAllLetters').addEventListener('click', event => {
    listAll().then(result => {
        let div = document.querySelector('.container-fluid');
        console.log(result.result);
        div.innerHTML = '';
        makeItPretty(result, div);
    })
});

document.getElementById('saveLetter').addEventListener('click', () => {
    document.querySelector('.container-fluid').innerHTML = '' +
        '<div class=\'container-fluid\'>' +
        '<input type=\'file\' id=\'fileUpload\'>' +
        '</div>';
    document.getElementById('fileUpload').addEventListener('change', (event) => {
        handleImageUpload(event)
    })
});

