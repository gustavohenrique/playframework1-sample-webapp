Ext.Loader.setConfig({enabled:true});
Ext.Loader.setPath('Ext.ux', '/public/js/extjs/ux');
Ext.application({
    name: 'PoupaNiquel',
    appFolder: '/public/js/app',
    autoCreateViewport: false,

    controllers: ['Filter',],
    
    launch: function() {
    	
        viewport = Ext.create('Ext.container.Viewport', {
            layout: 'border',
            items: [{
                region: 'center',
                autoScroll: true,
                border: false,
                autoScroll: true,
                style: 'padding: 15px; background: white',
                items: [{
                	region: 'west',
    				xtype: 'filterPanel'
                }]
            }, {
            	region: 'north',
            	height: 73,
            	tbar: [{
                	text: 'File',
                }, {
                	text: 'Edit',
                }, {
                	text: 'View',
                }, {
                	text: 'Help',
                }],
                bbar: [{
                	iconCls: 'transactions-icon',
                	cls:'x-btn-text-icon',
                	scale: 'large',
                	handler: 'teste'
                }, {
                	iconCls: 'transactions-icon',
                	cls:'x-btn-text-icon',
                	scale: 'large'
                }, {
                	iconCls: 'transactions-icon',
                	cls:'x-btn-text-icon',
                	scale: 'large'
                }, {
                	iconCls: 'transactions-icon',
                	cls:'x-btn-text-icon',
                	scale: 'large'
                }]
            }]
        });
    }
});