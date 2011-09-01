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
            }, {
            	region: 'west',
				xtype: 'filterPanel'
            }]
        });
    }
});