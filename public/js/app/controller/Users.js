Ext.define('PoupaNiquel.controller.Users', {
    extend: 'Ext.app.Controller',

    models: ['User'],
    stores: ['Users'],
    views:  ['users.Signup', 'users.Login'],
    
    init: function() {
    	this.control({
	        'login button[action=authenticate]': {
	            click: this.authenticate
	        },
	        'signup button[action=create]': {
	            click: this.create
	        }
    	});
    },
    
    create: function(button) {
    	var win = button.up('window'),
            form = win.down('form'),
    		values = form.getValues();
    	
    	if (form.getForm().isValid()) {
    		var user = new PoupaNiquel.model.User();
    		user.set(values);
    		user.save({
    		    success: function() {
    		    	window.location.href = '/'
    		    },
    		});
    	}
    },
    
    authenticate: function(button) {
    	var win = button.up('window'),
	        form = win.down('form').getForm();
		
    	if (form.isValid()) {
	    	form.submit({
	    	    url: '/users/authenticate',
	            method: 'POST',
	            scope:this,
	            
	            success: function(form, response) {
	            	window.location.href = '/'
	            },
	            
	            failure: function(form, response) {
	            	Ext.Msg.alert('Error', response.result.message);
	            }
	    	});
    	}
    }
    
});