Ext.define('PoupaNiquel.controller.Users', {
    extend: 'Ext.app.Controller',

    models: ['User'],
    stores: ['Users'],
    views:  ['users.Signup', 'users.Login'],
    
    init: function() {
    	this.control({
	        'loginPanel button[action=authenticate]': {
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
    		values = form.getValues(),
    		store = this.getUsersStore();
    	
    	if (form.getForm().isValid()) {
    		var user = new PoupaNiquel.model.User();
    		user.set(values);
    		console.log(user);
    		user.save({
    		    success: function() {
    		    	window.location.href = '/'
    		    },
    		});
    	}
    },
    
    authenticate: function(button) {
    	var form = button.up('form').getForm();
    	if (form.isValid()) {
	    	form.submit({
	    	    url: '/users/authenticate',
	            method: 'POST',
	            scope:this,
	            success: function(form, response) {
	                window.href.location = '/';
	//                 Ext.ux.Router.redirect('');
	            },
	            failure: function(form, response) {
	            	Ext.Msg.alert('Error', response.result.message);
	            }
	    	});
    	}
    }
    
});