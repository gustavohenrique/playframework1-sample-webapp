Ext.define('PoupaNiquel.view.common.MdiWindow', {
	extend: 'Ext.panel.Panel',
    alias: 'widget.mdiWindow',
	constrain: true,
    height: '100%',
    width: '100%',
    closable: true,
    closeAction: 'destroy',
    plain: true,
    layout: 'fit',
    
    initComponent: function() {
    	return this.callParent(arguments);
    },
    
    listeners: {
        render: function() {
        	var panels = Ext.ComponentQuery.query('mdiWindow');
        	
        	if (panels.length) {
        		for (var i=0; i < panels.length; i++) {
        			var item = panels[i];
        		    if (item.id != this.id) item.close();
        		};
        	}
        }
    }
});    