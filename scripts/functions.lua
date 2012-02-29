require("scripts/utils")
require("scripts/pos")

function patchAchivements(path, imageeditor, filePrefix, collection)
	for i = 1, 10, 1 do
		imageFilePath = string.format("%s/Data/Image/Title/Achievement/%s%d.png", path, filePrefix, i)
		local image = imageeditor:newImage(imageFilePath)
		achivementPadder(image);
		achivementWriter(image, lang[collection][i][1], lang[collection][i][2])
		image:save()
	end
end

function patchSpaceBar(imageeditor, path)
	for i = 1, 10, 1 do
		imageFilePath = string.format("%s/Data/Image/Title/PressAnyKey.png", path)
		local image = imageeditor:newImage(imageFilePath)
		title = image:newImage(image:getWidth(), image:getHeight())

		title:enableAntiAliasing()
		title:setColor(0xFFFFFFFF)
		title:setFont("Arita-Bold.ttf", 18.0, true)
		title:drawText(lang["press_spacebar"], 15, 15)
		title:setFilePath(imageFilePath)
		title:save()
	end
end

function patchNotices(path, imageeditor)
	for i = 1, 36, 1 do
		imageFilePath = string.format("%s/Data/Image/Stage/Notices/n%d.png", path, i)
		image = imageeditor:newImage(imageFilePath)
		noticePadder(image, pos["notice"][i][1], pos["notice"][i][2], pos["notice"][i][3], pos["notice"][i][4])
		image:enableAntiAliasing()
		image:setFont("Arita-Bold.ttf", 18)
		
		image:setColor(0xFF000000)
		multiLineTextWriter(image, pos["notice"][i][1] + 1, pos["notice"][i][2], 5, lang["notices"][i][1])
		
		image:setColor(0xFFF26522)
		multiLineTextWriter(image, pos["notice"][i][1] + 1, pos["notice"][i][2], 5, lang["notices"][i][2])
		
		image:save()
	end
end

function patchKoreanNotice(path, imageeditor)
	for i = 1, 36, 1 do
		image = imageeditor:newImage(path .. "/Data/Image/Title/KoreanNotice.png")
		
		image:setColor(0xFFA85B00)
		image:fillRect(pos["korean_notice"][1], pos["korean_notice"][2], pos["korean_notice"][3], pos["korean_notice"][4])
		
		image:enableAntiAliasing()
		image:setFont("Arita-Bold.ttf", 16)
		
		image:setColor(0xFF000000)
		multiLineTextWriter(image, pos["korean_notice"][1] + 1, pos["korean_notice"][2], 5, lang["korean_notice"])
		image:save()
	end
end