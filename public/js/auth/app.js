Ext.Loader.setConfig({enabled:true});

Ext.application({
    name: 'PoupaNiquel',
    appFolder: '/public/js/auth',
    autoCreateViewport: false,

    controllers: ['Auth'],
    
    launch: function() {
        viewport = Ext.create('Ext.container.Viewport', {
        	id: 'viewport',
            layout: 'border',
            items: [{
            	id: 'viewportCenter',
                region: 'center',
                border: false,
                autoScroll: true,
                items: [{
                	xtype: 'loginPanel',
                	autoShow: 'true'
                }]

            }, {
            	region: 'north',
            	height: 45,
            	xtype: 'authMenu',
            }]
        });
    }
});