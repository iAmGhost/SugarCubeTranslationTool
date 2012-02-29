function string:split(sep)
        local sep, fields = sep or ":", {}
        local pattern = string.format("([^%s]+)", sep)
        self:gsub(pattern, function(c) fields[#fields+1] = c end)
        return fields
end

function achivementPadder(image)
	for i = 0, 200, 1 do
		image:copyArea(60, 0, 1, 60, i, 0)
	end
end

function achivementWriter(image, titleText, messageText)
	image:enableAntiAliasing()
	image:setColor(0xFFA77E21)
	image:setFont("Arita-Bold.ttf", 16.0)
	image:drawText(titleText, 65, 10)
	
	image:setColor(0xFF9E9180)
	image:setFont("Arita-Bold.ttf", 12.0)
	image:drawText(messageText, 65, 30)
end

function noticePadder(image, x, y, width, height)
	image:deleteArea(x + 1, y, width - 1, height)
	
	for i = 0, width - 1, 1 do
		image:copyArea(x, y, 1, height, i, 0)
	end
end

function multiLineTextWriter(image, x, y, spacing, text)
	array = text:split("\n")
	
	for index, value in ipairs(array) do
		image:drawText(value, x, (spacing*index-1) + y + (index-1)*image:getFontSize())
	end
end