Ext.define('PoupaNiquel.view.common.MdiWindow', {
	extend: 'Ext.panel.Panel',
    alias: 'widget.mdiWindow',
	constrain: true,
    height: '100%',
    width: '100%',
    closable: true,
    plain: true,
    layout: 'fit',
    
    initComponent: function() {
    	return this.callParent(arguments);
    },
    
    listeners: {
        render: function() {
        	var panels = Ext.ComponentQuery.query('mdiWindow');
        	
        	if (panels.length) {
        		Ext.Array.each(panels, function(item, index, othersItSelf) {
        		    if (item.id != self.id) item.hide();
        		});
        	}
        }
    }
});    