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
	        'signupPanel button[action=register]': {
	            click: this.register
	        }
    	});
    },
    
    register: function(button) {
    	var form = button.up('form'),
    		values = form.getValues(),
    		store = this.getUsersStore();
    	
    	if (form.getForm().isValid()) {
    		try {
    		    store.add(values);
    		    store.sync();
    		}
    		catch(error) {}
    		
    		console.log("fim");
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