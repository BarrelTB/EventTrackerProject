window.addEventListener('load', function(e) {
	console.log('document loaded');
	getStrains();
});

function getSessions(strains) {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/sessions');

	xhr.onreadystatechange = function() {

		if (xhr.status < 400 && xhr.readyState === 4) {
			var sessions = JSON.parse(xhr.responseText);
			displaySessions(sessions, strains);
		}

		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ": " + xhr.responseText);
		}
	};
	xhr.send(null);
}

function displaySessions(sessions, strains) {

	var table = document.createElement('table');
	table.border = 1;
	table.setAttribute('id', 'seshTable');

	var thead = document.createElement('thead');
	table.appendChild(thead);

	var tr = document.createElement('tr');

	var th = document.createElement('th');
	th.textContent = 'ID';
	tr.appendChild(th);

	var th1 = document.createElement('th');
	th1.textContent = 'Title';
	tr.appendChild(th1);

	var th2 = document.createElement('th');
	th2.textContent = 'Time';
	tr.appendChild(th2);

	var th3 = document.createElement('th');
	th3.textContent = 'Description';
	tr.appendChild(th3);

	var th4 = document.createElement('th');
	th4.textContent = 'Strain';
	tr.appendChild(th4);

	thead.appendChild(tr);

	var tbody = document.createElement('tbody');
	
	totalTime = 0;

	for (let i = 0; i < sessions.length; i++) {

		var tr1 = document.createElement('tr');
		tr1.addEventListener('click', function(e) {
			displaySesh(sessions[i], strains);
		});
		var td = document.createElement('td');
		td.textContent = sessions[i].id;
		tr1.appendChild(td);

		var td1 = document.createElement('td');
		td1.textContent = sessions[i].title;
		tr1.appendChild(td1);

		var td2 = document.createElement('td');
		td2.textContent = sessions[i].timeLength;
		tr1.appendChild(td2);
		findTime(sessions[i].timeLength);

		var td3 = document.createElement('td');
		td3.textContent = sessions[i].desc;
		tr1.appendChild(td3);

		var td4 = document.createElement('td');
		td4.textContent = sessions[i].strain.name;
		tr1.appendChild(td4);

		tbody.appendChild(tr1);
	}

	table.appendChild(tbody);
	
	var timeDisplay = document.createElement('p');
	timeDisplay.textContent = "You've smoked for : " +  totalTime + " hours. (very lose estimation)";

	document.body.appendChild(table);
	table.appendChild(timeDisplay);
	createBr(document.body);
	createBr(document.body);
}

function displayForm(strains) {

	var form = document.createElement('form');

	form.setAttribute('name', 'create');
	form.setAttribute('id', 'createForm');

	var title = document.createElement('input');
	title.setAttribute('name', 'title');
	title.setAttribute('type', 'text');
	title.setAttribute('placeHolder', 'Title');

	form.appendChild(title);
	createBr(form);

	var time = document.createElement('input');
	time.setAttribute('name', 'time');
	time.setAttribute('type', 'text');
	time.setAttribute('placeHolder', 'Time (in words if you must)');

	form.appendChild(time);
	createBr(form);

	var desc = document.createElement('input');
	desc.setAttribute('name', 'desc');
	desc.setAttribute('type', 'text');
	desc.setAttribute('placeHolder', 'Description (tell us what happened)');

	form.appendChild(desc);
	createBr(form);

	var strain = document.createElement('select');
	strain.setAttribute('name', 'strainselect');
	form.appendChild(strain);
	var allStrains = strains;

	for (let i = 0; i < allStrains.length; i++) {
		var option = document.createElement('option');
		option.text = allStrains[i].name;
		strain.add(option);
	}

	var submit = document.createElement('input');
	submit.name = 'submit'; // assign a name attribute
	submit.type = 'submit'; // assign a type attribute
	submit.value = 'Submit Form'; // assign a value attribute

	submit.addEventListener('click', function(e) { // Assign an event listener
													// to the submit button
													// variable

		e.preventDefault();
		var form = e.target.parentElement;

		// print the fname value to the console
		var strainId = 0;

		var opt = form.strainselect.options[strain.selectedIndex];

		for (let i = 0; i < allStrains.length; i++) {
			if (opt.value == allStrains[i].name) {
				strainId = allStrains[i].id;
			}
		}

		var sesh = {
			title : form.title.value,
			timeLength : form.time.value,
			desc : form.desc.value,
			strain : {
				id : strainId
			}
		}
		console.log(sesh);
		createSesh(sesh);

		// clear the form data
		form.reset();
	});

	// add the input to the form
	form.appendChild(submit);

	// add the form to the body
	document.body.appendChild(form);

	document.body.appendChild(form);
}

function getStrains() {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/strains');

	xhr.onreadystatechange = function() {

		if (xhr.status < 400 && xhr.readyState === 4) {
			var strains = JSON.parse(xhr.responseText);
			displayForm(strains);
			getSessions(strains);
		}

		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ": " + xhr.responseText);
		}
	};
	xhr.send(null);
}

function createBr(item) {
	var br = document.createElement('br');
	item.appendChild(br);
}

function createSesh(sesh) {
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/sessions');

	xhr.setRequestHeader('Content-type', 'application/json');

	xhr.onreadystatechange = function() {

		if (xhr.status < 400 && xhr.readyState === 4) {
			var newSesh = JSON.parse(xhr.responseText);
			var el = document.getElementById('seshTable');
			var el1 = document.getElementById('createForm');
			if (el !== null && el1 !== null) {
				el.remove();
				el1.remove();
				
				getStrains();
			} else {
				getStrains();
			}

		}

		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ": " + xhr.responseText);
		}
	};
	var seshStringify = JSON.stringify(sesh);
	xhr.send(seshStringify);
}

function displaySesh(session, strains) {
	var table = document.getElementById('seshTable');
	var form = document.getElementById('createForm');
	table.setAttribute('hidden', true);
	form.setAttribute('hidden', true);
	
	console.log(session);
	
	var div = document.createElement('div');
	div.setAttribute('id', 'detailDiv');

	var h1 = document.createElement('h1');
	h1.setAttribute('value', session.title);
	h1.textContent = session.title;
	console.log(session.title);

	var ul = document.createElement('ul');

	var li1 = document.createElement('li');
	li1.setAttribute('value', session.id);
	li1.textContent = session.id;
	ul.appendChild(li1);
	

	var li2 = document.createElement('li');
	li2.setAttribute('value', session.timeLength);
	li2.textContent = session.timeLength;
	ul.appendChild(li2);

	var li3 = document.createElement('li');
	li3.setAttribute('value', session.desc);
	li3.textContent = session.desc;
	ul.appendChild(li3);

	var li4 = document.createElement('li');
	li4.setAttribute('value', session.strain.name);
	li4.textContent = session.strain.name;
	ul.appendChild(li4);
	
	var delBtn = document.createElement('button');
	delBtn.setAttribute('type', 'button');
	delBtn.textContent = 'Delete';
	delBtn.addEventListener('click', function(e){
		deleteSesh(session);
	});
	
	div.appendChild(delBtn);
	
	var btn = document.createElement('button');
	btn.setAttribute('type', 'button');
	btn.textContent = 'Edit';
	btn.addEventListener('click', function(e) {
		displayEdit(session, strains);
	});
	
	div.appendChild(btn);
	
	div.appendChild(h1);
	
	div.appendChild(ul);
	
	document.body.appendChild(div);
}

function displayEdit(session, strains) {
	var div = document.getElementById('detailDiv');
	div.setAttribute('hidden', true);
	
	var form = document.createElement('form');

	form.setAttribute('name', 'edit');
	form.setAttribute('id', 'editForm');

	var title = document.createElement('input');
	title.setAttribute('name', 'title');
	title.setAttribute('type', 'text');
	title.setAttribute('value', session.title);

	form.appendChild(title);
	createBr(form);

	var time = document.createElement('input');
	time.setAttribute('name', 'time');
	time.setAttribute('type', 'text');
	time.setAttribute('value', session.timeLength);

	form.appendChild(time);
	createBr(form);

	var desc = document.createElement('input');
	desc.setAttribute('name', 'desc');
	desc.setAttribute('type', 'text');
	desc.setAttribute('value', session.desc);

	form.appendChild(desc);
	createBr(form);

	var strain = document.createElement('select');
	strain.setAttribute('name', 'strainselect');
	form.appendChild(strain);
	var allStrains = strains;

	for (let i = 0; i < allStrains.length; i++) {
		var option = document.createElement('option');
		option.text = allStrains[i].name;
		strain.add(option);
	}

	var submit = document.createElement('input');
	submit.name = 'submit'; // assign a name attribute
	submit.type = 'submit'; // assign a type attribute
	submit.value = 'Submit Form'; // assign a value attribute

	submit.addEventListener('click', function(e) { // Assign an event listener
													// to the submit button
													// variable

		e.preventDefault();
		var form = e.target.parentElement;

		// print the fname value to the console
		
		var seshId = session.id;
		var strainId = 0;

		var opt = form.strainselect.options[strain.selectedIndex];

		for (let i = 0; i < allStrains.length; i++) {
			if (opt.value == allStrains[i].name) {
				strainId = allStrains[i].id;
			}
		}

		var sesh = {
			title : form.title.value,
			timeLength : form.time.value,
			desc : form.desc.value,
			strain : {
				id : strainId
			}
		}
		console.log(sesh);
		updateSesh(sesh, seshId);

		// clear the form data
		form.reset();
	});

	// add the input to the form
	form.appendChild(submit);

	// add the form to the body
	document.body.appendChild(form);

	document.body.appendChild(form);
}

function updateSesh(sesh, seshId) {
	var xhr = new XMLHttpRequest();
	xhr.open('PUT', 'api/sessions/' + seshId);

	xhr.setRequestHeader('Content-type', 'application/json');

	xhr.onreadystatechange = function() {

		if (xhr.status < 400 && xhr.readyState === 4) {
			var newSesh = JSON.parse(xhr.responseText);
			var el = document.getElementById('seshTable');
			var el1 = document.getElementById('createForm');
			var el2 = document.getElementById('editForm');
			var el3 = document.getElementById('detailDiv');
			if (el !== null && el1 !== null) {
				el.remove();
				el1.remove();
				el2.remove();
				el3.remove();
				
				getStrains();
			} else {
				getStrains();
			}

		}

		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ": " + xhr.responseText);
		}
	};
	var seshStringify = JSON.stringify(sesh);
	xhr.send(seshStringify);
}
function deleteSesh(session) {
	var xhr = new XMLHttpRequest();
	xhr.open('DELETE', 'api/sessions/' + session.id);
	
	xhr.setRequestHeader('Content-type', 'application/json');
	
	xhr.onreadystatechange = function() {
		
		if (xhr.status < 400 && xhr.readyState === 4) {
			var el = document.getElementById('seshTable');
			var el1 = document.getElementById('createForm');
			var el2 = document.getElementById('detailDiv');
			if (el !== null && el1 !== null) {
				el.remove();
				el1.remove();
				el2.remove();
				
				getStrains();
			} else {
				getStrains();
			}
			
		}
		
		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ": " + xhr.responseText);
		}
	};
	xhr.send(null);
}

var totalTime = 0;

function findTime(str) { 
    var matches = parseInt(str.match(/(\d+)/)); 
    if (typeof(matches) == 'number' && !isNaN(matches)) {
    	totalTime += matches;
    	
    }
} 
