Ext.define('PoupaNiquel.controller.Importers', {
    extend: 'Ext.app.Controller',

    views: ['common.MdiWindow'],
    
    init: function() {
    	this.control({
    		'form button[action=import]': {
  	    	    click: this.import
  	        }
    	})
    },
    
    showPanel: function() {
    	var viewport = Ext.ComponentManager.get('viewportCenter'),
    	    panel = Ext.ComponentManager.get('importPanel');
    	
    	if (panel == null) {
       	    panel = Ext.create('widget.mdiWindow', {
	    		id: 'importPanel',
	            title: 'Import...',
	            
	            items: [{
	            	xtype: 'form',
	            	bodyPadding: 10,
	                
	            	items: [{
	            		xtype: 'filefield',
	            		name: 'file',
	            		label: 'Select file to import',
	            		width: 300,
	            		labelWidth: 50,
		                allowBlank: false,
		                msgTarget: 'side',
	            	}, {
	            		xtype: 'button',
            			text: 'Ok',
            			action: 'import',
            			width: 100,
            			handler: function() {
            				var form = this.up('form').getForm();
            				
            	            if (form.isValid()) {
            	                form.submit({
            	                	url: '/importers/upload',
            	                	method: 'POST',
            	                	waitMsg: 'Uploading your file...',
            	                	
            	    	            success: function(form, response) {
            	    	            	Ext.Msg.alert('Success', response.result.message);
            	    	            },
            	    	            
            	    	            failure: function(form, response) {
            	    	            	Ext.Msg.alert('Error', response.result.message);
            	    	            }
            	    	            
            	                });
            	            }
            			}
		            }]
	            	
	            }],
	            
	    	});
       	    viewport.add(panel);
       	    panel.show();
    	}
    },
    
    import: function(button) {
    	console.log("importando...");
    },
    
    
});