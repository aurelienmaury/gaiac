package gaiac

class FlashTagLib {

	static namespace = "flash"
	
	def success = { 
		if (flash.success)
			out << render(template:'/common/typedFlash', model: ['type':'success'])
	}
	
	def warning = {
		if (flash.warning)
			out << render(template:'/common/typedFlash', model: [type:'warning'])
	}
	
	def error = {
		if (flash.error)
			out << render(template:'/common/typedFlash', model: [type:'error'])
	}
	
	def info = {
		if (flash.info)
			out << render(template:'/common/typedFlash', model: [type:'info'])
	}
	
	def all = {
		if (flash.error)
			out << render(template:'/common/typedFlash', model: [type:'error'])
		if (flash.warning)
			out << render(template:'/common/typedFlash', model: [type:'warning'])
		if (flash.success)
			out << render(template:'/common/typedFlash', model: [type:'success'])
		if (flash.info)
			out << render(template:'/common/typedFlash', model: [type:'info'])
	}
}
