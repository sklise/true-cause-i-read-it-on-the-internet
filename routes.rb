get '/' do
  offset = rand(Fact.count+1)
  @fact = Fact.find(:first, :conditions => ["id >= :offset", {:offset => offset}])
  erb :fact
end

# Use Sass with `views/style.scss` as your stylesheet
# To use, delete `public/stylehseets/style.css`
get '/stylesheets/style.css' do
  scss :style
end

get '/favicon.ico' do
  erb :notfound
end

get '/Favicon.ico' do
  erb :notfound
end

get '/:fact' do
  if @fact = Fact.find(:first, :conditions => ["url = :url", {:url => params[:fact]}])
  else
    @fact = Fact.create(:url => params[:fact], :content => params[:fact].split('-').join(" "))
  end
  erb :fact
end

#   404
#---------------------------------------

not_found do
  erb :notfound
end