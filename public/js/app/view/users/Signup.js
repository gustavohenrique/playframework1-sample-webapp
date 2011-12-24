Ext.define('PoupaNiquel.view.users.Signup', {
	extend: 'Ext.window.Window',
    alias: 'widget.signupPanel',

    title: 'Sign Up',
    width: 300,
    height: 200,
    layout: 'fit',
    border: 0,
    closable: false,
    plain: true,
    
    items: [{
    	xtype: 'form',
    	bodyPadding: 10,
    	
    	fieldDefaults: {
            labelWidth: 60,
            anchor: '100%',
            allowBlank: false,
            msgTarget: 'side',
            selectOnFocus: true,
            enableKeyEvents: true,
        },
        
    	items: [{
    		fieldLabel: 'Name',
    		xtype: 'textfield',
    		name: 'fullname',
        }, {
    		fieldLabel: 'E-mail',
    		xtype: 'textfield',
    		name: 'username',
        }, {
        	fieldLabel: 'Password',
           	xtype: 'textfield',
           	name: 'password',
           	inputType: 'password',
        }],
        
        dockedItems: [{
        	xtype: 'toolbar',
	        dock: 'bottom',
	        id:'buttons',
	        ui: 'footer',
	        items: ['->', {
	            text: 'Ok',
	            action: 'register',
	            width: 100,
	        }]
           
    	}]
    }],
    
    initComponent: function() {
        return this.callParent(arguments);
    }
});